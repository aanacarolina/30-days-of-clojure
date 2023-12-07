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


(defn eligible-games [game-results]
  (map second (map first game-results)))

(defn game-id [eligible-game-results]
  (map first (map first eligible-game-results)))

#_(defn results->map [game-results]
  (map (apply hash-map (flatten (map  #(re-seq #"[a-z]+|[0-9]+" %) game-results)))))

(defn split-on-space [s]
  (str/split (str/triml s) #"\s+"))

(defn results->map [games-results]
  (map split-on-space games-results))

(defn possible-sum [input]
  (->> input
       input->data
       (map games-results)
       results->map))


(comment
  (possible-sum "day2_exinput.txt")
  (possible-sum "day2_input.txt")
  (input->data "day2_input.txt")
  (input->data "day2_exinput.txt")
  (eligible-games (map games-results (input->data "day2_exinput.txt")))
  (map first (map first (map games-results (input->data "day2_exinput.txt"))))
  (map games-results (input->data "day2_exinput.txt"))
  (results->map ["Game 1" " 3 blue" " 4 red"])
  ;=> (["Game" "1"] ["3" "blue"] ["4" "red"])
  )


 