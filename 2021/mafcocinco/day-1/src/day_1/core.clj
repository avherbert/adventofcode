(ns day-1.core
  (:require [clojure.string :as string]))

;; Assumes there is a data file at `./resources/puzzel_1_input.dat`
;; This ingests the file, splits the string on \n and converts each string to an integer
;; The result is a list of integers like `(1 23 45 42 19 ...)`
(def data (map #(Integer/parseInt %) (-> (slurp "./resources/day_1_input.dat") (string/split #"\n"))))

(defn part-1
  ([]
   (part-1 data))
  ([d]
   (->> (partition 2 1 d)                  ;; Partition sequence into ((1 23) (23 45) (45 42) (42 19) (19 ...))
        (filter #(< (first %) (second %))) ;; Keep the `increase` pairs
        (count))))                         ;; Count them

(defn part-2 []
  (->> (partition 3 1 data) ;; Partion sequence into ((1 23 45) (23 45 42) (45 42 19) (42 19 ...))
       (map #(apply + %))   ;; Sum each tuple
       (part-1)))
