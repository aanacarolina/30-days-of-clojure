(ns yr2023.day4.scratchcards
  (:require [clojure.string :as str]
             [clojure.set :as set]
            [clojure.math :as math]))

(defn input->data [input]
  (str/split-lines (slurp input)))

(defn parse-card
  [card]
  (let [card-array (map str/trim (str/split card #"\:|\|")) 
        my-numbers (set (map parse-long (str/split (second card-array) #"\s+")))
        wining-numbers (set (map parse-long (str/split (last card-array) #"\s+")))]
    [my-numbers wining-numbers]))

(defn wining-numbers [parsed-card]
  (set/intersection (first parsed-card) (second parsed-card)))

(defn score [numbers-set]
  (if (pos? (count numbers-set))
    (long (math/pow 2 (dec (count numbers-set))))
    0))

(defn scratchcards-points [input]
  (->> input
       input->data
       (map parse-card)
       (map wining-numbers)
       (map score)
       (reduce +)
       ))

(comment

  (def input "inputs/day4_input.txt")
  (scratchcards-points input)
  (map score '(#{86 48 17 83} #{32 61} #{1 21} #{84} #{} #{}))

  )


