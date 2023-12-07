(ns advent-of-code.day2.cubes
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(def max-cubes {"red" 12 "green" 12 "blue" 14})

(defn input->data [input]
  (str/split-lines (slurp input)))

#_(defn get-game-id [game-info]
  (last (str/split (first (str/split game-info #":")) #" ")))

(defn games-results [game-info]
  (into [] 
        (map 
         #(str/split % #":|,") 
         (str/split game-info #";"))))

(defn game-id [game-results]
  (map first (map first game-results)))

(defn results->map [game-results]
  (map (apply hash-map (flatten (map  #(re-seq #"[a-z]+|[0-9]+" %) game-results)))))

(defn eligible-games [])

(defn possible-sum [input]
  (->> input
       input->data
       (map games-results) 
       ))


(comment
  (possible-sum "day2_exinput.txt")
  (possible-sum "day2_input.txt")
  (input->data "day2_input.txt")
  (input->data "day2_exinput.txt") 
  
  (map first (map first (map games-results ["Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
                                            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue"
                                            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"
                                            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red"
                                            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"])))
  )


 