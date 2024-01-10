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
    {:card card-id :my-numbers my-numbers :winning wining-numbers :matches 0 :copies 1}))


(defn count-matches [parsed-card]
  (count (set/intersection (:winning parsed-card) (:my-numbers parsed-card))))

(defn matches [parsed-card]
  (let [matches (count-matches parsed-card)]
    (assoc parsed-card :matches matches)))

(defn last-card [all-cards]
  (count all-cards))

(defn calculate-copies 
  "add N COPIES to the next N cards"
  [rest-cards n-copies matches]
  (map-indexed 
   (fn [i card] 
     (if (< i matches)
       (update card :copies + n-copies)
       card)) 
   rest-cards))

(comment 
  
  (calculate-copies [{:copies 1} ] 2 1)
  )



;blockers
;was thinking of map, not the array :keys
;was not able to separate rounds and the queue

(defn score [cards] 
  (loop [score 0 
         queue cards] 
    (if-not (seq queue) 
      score 
      (let [[card & rest-cards] queue 
            {:keys [matches copies]} card] 
        (recur (+ score copies) 
               (calculate-copies rest-cards copies matches))))))


(defn scratchcards-points [input]
  (->> input
       input->data
       (map parse-card)
       (map matches)
       score
       ))

(comment

  (def input "inputs/day4_input.txt")
  (def exinput "inputs/day4_exinput.txt")
  (time (scratchcards-points input))
  (scratchcards-points exinput) 
  (def all-parsed (map parse-card (input->data exinput)))
  (last-card all-parsed)
  (count-matches {:card 4, :my-numbers #{69 92 41 73 84}, :winning #{59 58 54 51 76 5 83 84}, :matches 0, :copies 5}))

