(ns day-2.core
  (:require [clojure.string :as string]))

(def position {:horizontal 0 :depth 0 :aim 0})

(defn move-part-1
  "Moves specified direction using rules from part 1"
  [position [direction amount]]
  (let [amount (Integer/parseInt amount)]                ;; convert string to int
    (case (keyword direction)                            ;; convert direction to keyword for use in case form
      :up (update position :depth - amount)              ;; `up` case, subtract `amount` from `depth`
      :down (update position :depth + amount)            ;; `down` case, add `amount` to `depth`
      :forward (update position :horizontal + amount)))) ;; `forward` case, add `amount` to `horizontal`

(defn forward
  "Handles `forward` command for part 2"
  [{:keys [aim] :as position} amount]           ;; Destructor position, binding `aim` value
  (-> (update position :depth + (* aim amount)) ;; add `aim` * `amount` to `depth`
      (update :horizontal + amount)))           ;; add `amount` to `horizontal`

(defn move-part-2
  "Moves specified direction using rules from part 2"
  [position [direction amount]]
  (let [amount (Integer/parseInt amount)]   ;; convert string to int
    (case (keyword direction)               ;; convert direction to keyword for use in case form
      :up (update position :aim - amount)   ;; `up` case, subtract `amount` from `depth`
      :down (update position :aim + amount) ;; `down` case, add `amount` to `depth`
      :forward (forward position amount)))) ;; apply `forward` function

(defn calculate-answer
  "Calculates the answer"
  [position]
  (->> (select-keys position [:horizontal :depth]) ;; Extract `horizontal` and `depth` for calculation
       (vals)                                      ;; Get `vals` from `position` map
       (apply *)))                                 ;; Apply multiplication to each int in sequence

(defn calculate
  "Reads data from the data file, transforming it into a usable state"
  [move-fn]
  (let [data (-> (slurp "./resources/puzzle_input.dat") (string/split #"\n"))] 
    (->> (map #(string/split % #" ") data) ;; String -> 2-tuple
         (reduce move-fn position)         ;; Reduce across data using `move-fn`, starting at `position`
         (calculate-answer))))             ;; Calculate the answer

(defn part-1 [] (calculate move-part-1))
(defn part-2 [] (calculate move-part-2))
