(ns advent-of-code.day2.cubes
  (:require [clojure.string :as str]))

(def max-cubes {"red" 12 "green" 12 "blue" 14})

(defn input->data [input]
  (str/split-lines (slurp input)))

(defn get-game-id [game-info]
  (last (str/split (first (str/split game-info #":")) #" ")))

(defn games-results [game-info]
  (map #(str/split % #",") (str/split (last (str/split game-info #":")) #";")))

(defn results->map [game-results]
  (flatten (map  #(re-seq #"[a-z]+|[0-9]+" %) game-results))
  )

(defn eligible-games [])

(defn possible-sum [input]
  (->> input
       input->data 
       (map get-game-id)))

(comment
  (possible-sum "day2_exinput.txt")
  (possible-sum "day2_input.txt")
  (input->data "day2_input.txt")
  (input->data "day2_exinput.txt")
  (get-game-id "Game 50: 10 red, 3 blue, 6 green; 1 blue, 5 red, 3 green; 6 blue, 11 red, 12 green; 10 green")
  (games-results "Game 50: 10 red, 3 blue, 6 green; 1 blue, 5 red, 3 green; 6 blue, 11 red, 12 green; 10 green")
  ;=> ([" 10 red" " 3 blue" " 6 green"] [" 1 blue" " 5 red" " 3 green"] [" 6 blue" " 11 red" " 12 green"] [" 10 green"])
  (results->map [" 10 red" " 3 blue" " 6 green"])
  ;=> ("10" "red" "3" "blue" "6" "green")
  )


 