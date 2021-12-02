(ns day-2.core
  (:require [clojure.string :as string]))

(def position )

(defn forward
  "Handles `forward` command for part 2"
  [{:keys [aim] :as position} amount]            ;; Destructure position, binding `aim` value
  (cond-> (update position :horizontal + amount) ;; add `amount` to `horizontal`
    (int? aim)                                   ;; When `aim` is an integer...
    (update :depth + (* aim amount))))           ;; ... update `depth` with `aim` * `amount` 

(defn depth
  "Conditionally handles depth adjustments"
  [{:keys [aim] :as position} direction-fn amount] ;; Destructure position, binding `aim` value
  (if (int? aim)                                   ;; If `aim` is an integer...
    (update position :aim direction-fn amount)     ;; ... adjust the value of `aim`
    (update position :depth direction-fn amount))) ;; ... otherwise adjust the value of `depth`

(defn move
  "Moves specified direction using rules from part 1"
  [position [direction amount]]
  (let [amount (Integer/parseInt amount)]   ;; convert string to int
    (case (keyword direction)               ;; convert direction to keyword for use in case form
      :up (depth position - amount)         ;; adjust `depth` down by `amount`
      :down (depth position + amount)       ;; adjust `depth` up by `amount`
      :forward (forward position amount)))) ;; adjust `forward` by `amount`

(defn final-position
  "Calculates the answer"
  [position]
  (->> (select-keys position [:horizontal :depth]) ;; Extract `horizontal` and `depth` for calculation
       (vals)                                      ;; Get `vals` from `position` map
       (apply *)))                                 ;; Apply multiplication to each int in sequence

(defn navigate
  "Reads data from the data file, transforming it into a usable state"
  [position]
  (let [data (-> (slurp "./resources/puzzle_input.dat") (string/split #"\n"))]
    (->> (map #(string/split % #" ") data) ;; String -> 2-tuple
         (reduce move position)            ;; Reduce across data using `move-fn`, starting at `position`
         (final-position))))               ;; Calculate the answer

(defn part-1 [] (navigate {:horizontal 0 :depth 0}))
(defn part-2 [] (navigate {:horizontal 0 :depth 0 :aim 0}))
