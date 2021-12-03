(ns day-3.core
  (:require [clojure.string :as string]))

(def data (mapv #(string/split % #"") (-> (slurp "./resources/puzzle_input.dat") (string/split #"\n"))))

(defn rotate [bits] (apply map vector bits)) ;; [[1 2 3] [4 5 6]] -> [[1 4] [2 5] [3 6]]

(defn binary->decimal [binary] (-> (apply str binary) (Integer/parseInt 2)))

(defn winner?
  "Returns `true` if the specified `bit-value` satisfies `comp`, `false` otherwise"
  [comp bits bit-value]
  (let [c (-> (filter #(= % bit-value) bits) (count))]
    (comp c (- (count bits) c))))

(defn flip [bit] (if (= bit "1") "0" "1"))

(defn aggregate-bit [comp bit bits] (if (winner? comp bits bit) bit (flip bit)))

(defn gamma [] (->> (rotate data) (map #(aggregate-bit > "1" %))))

(defn epsilon [gamma] (map flip gamma))

(defn life-support-rating
  "Calculates the oxygen generator rating following rules in part-2 of day-3"
  [comp bit]
  (loop [potential-answers data
         position 0]
    (let [bits (apply mapv vector potential-answers)]
      (cond
        ;; Found the answer!
        (= (count potential-answers) 1)
        (-> (first potential-answers) (binary->decimal))

        ;; This should not happen, throw an exception
        (empty? potential-answers)
        (throw (ex-info "data is invalid" {}))

        ;; Otherwise, we filter out potential anwers
        :else
        (-> (filter #(= (aggregate-bit comp bit (nth bits position)) (nth % position)) potential-answers)
            (recur (inc position)))))))

(defn oxygen-generator-rating [] (life-support-rating >= "1"))

(defn co2-generator-rating [] (life-support-rating <= "0"))

(defn part-1 [] (* (binary->decimal (gamma)) (binary->decimal (epsilon (gamma)))))

(defn part-2 [] (* (oxygen-generator-rating) (co2-generator-rating)))
