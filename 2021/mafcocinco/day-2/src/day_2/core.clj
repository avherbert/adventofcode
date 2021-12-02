(ns day-2.core
  (:require [clojure.string :as string]))

(defn forward
  "Handles `forward` command"
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
  "Moves specified direction"
  [position [direction amount]]
  (let [amount (Integer/parseInt amount)]   ;; convert string to int
    (case (keyword direction)               ;; convert direction to keyword for use in case form
      :up (depth position - amount)         ;; adjust `depth` down by `amount`
      :down (depth position + amount)       ;; adjust `depth` up by `amount`
      :forward (forward position amount)))) ;; adjust `forward` by `amount`

(defn final-position
  "Calculates the final position"
  [{:keys [horizontal depth]}]
  (* horizontal depth)) ;; Multiply `horizontal` by `depth`

(defn navigate
  "Reads navigation commands, processing each one and obtaining a final position"
  [starting-position]
  (let [data (-> (slurp "./resources/puzzle_input.dat") (string/split #"\n"))]
    (->> (map #(string/split % #" ") data) ;; String -> 2-tuple
         (reduce move starting-position)   ;; Reduce across data using `move`, starting at `starting-position`
         (final-position))))               ;; Calculate the answer

(defn part-1 [] (navigate {:horizontal 0 :depth 0}))
(defn part-2 [] (navigate {:horizontal 0 :depth 0 :aim 0}))
