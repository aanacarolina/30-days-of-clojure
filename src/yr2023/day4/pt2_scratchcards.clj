(ns yr2023.day4.pt2-scratchcards
   (:require [clojure.string :as str]
             [clojure.set :as set]
             [clojure.math :as math]))

 (defn input->data [input]
   (str/split-lines (slurp input)))

 (defn parse-card
   [card]
   (let [card-array (map str/trim (str/split card #"\:|\|"))
         card-id (parse-long (last (str/split (first card-array) #"\s+")))
         my-numbers (set (map parse-long (str/split (second card-array) #"\s+")))
         wining-numbers (set (map parse-long (str/split (last card-array) #"\s+")))]
     [card-id my-numbers wining-numbers]))

 (defn wining-numbers [parsed-card]
   (set/intersection (second parsed-card) (last parsed-card)))

 (defn sum-all-cards-ids [parse-cards]
   (reduce + (map first parse-cards)))
 
 (defn sum-all-winning-numbers [parsed-cards]
   (reduce + (map count (map wining-numbers parsed-cards))))
 
 (defn score [parsed-card]
   (let [points (count (remove nil? (wining-numbers parsed-card)))
         card-id (first parsed-card)] 
     #_(if (>= points 0)
         (+ card-id points)
         (+ card-id 0))
     [card-id points]))

 (defn scratchcards-points [input]
   (->> input
        input->data
        (map parse-card) 
        (map score)
        (reduce +)))

 (comment

   (def input "inputs/day4_input.txt")
   (def exinput "inputs/day4_exinput.txt")
   (time (scratchcards-points input))
   (scratchcards-points exinput)
   (def all-parsed (map parse-card (input->data input))) 
   (time (+ (sum-all-cards-ids all-parsed) (sum-all-winning-numbers all-parsed)))
   

   (wining-numbers [1 #{7 72 15 99 25 34 3 30 42 63} #{72 55 77 95 92 15 75 32 41 28 64 51 25 3 2 16 87 30 73 10 18 71 37 63 49}])
   )

