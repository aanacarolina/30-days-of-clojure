(ns advent-of-code.day2.pt2_cubes
  (:require [clojure.string :as str]))

;--------------- input parser ----------------

(defn input->data
  "reads input (one big string) and based on line breaks splits into smallers strings (this string is a game and its turns)"
  [input]
  (str/split-lines (slurp input)))

(defn split-turns
  "receveis a string of turns and splits by semicolon "
  [turns]
  (str/split turns #"\s*;\s*"))

(defn parse-take
  "the args are: turn is an empty map {} that we will be populating with each string pair (\" 1 blue \")"
  [turn string]
  (let [[_ v k] (re-matches #"(\d+) (red|green|blue)" string)]
    (assoc turn (keyword k) (parse-long v))))

(defn parse-comma
  "splits by comma and remove spaces to form a take"
  [turn]
  (str/split turn #"\s*,\s*"))

(defn parse-turns
  "parses each turn and creates to the desired :turns structure (vector of maps containing each structured turn) "
  [string]
  (->> (split-turns string)
       (map parse-comma)
       (map #(reduce parse-take {} %))
       (into [])))

(defn data->games-map
  "turns a full game info string into a map containing :game and :turns formatted"
  [game-info]
  (let [[_ k v] (re-matches #"Game (\d+): (.*)" game-info)]
    {:game-id (parse-long k) :turns (parse-turns v)}))

;----------puzzle logic---------------

(defn turns-power
  [game]
  (let [blue (apply max (do (remove nil? (map :blue (:turns game)))))
        red (apply max (remove nil? (map :red (:turns game))))
        green (apply max (remove nil? (map :green (:turns game))))]
    (* blue red green)))

;----------puzzle output---------------

(defn games-power-sum
  "Calculates the sum of game IDs that would have been possible, given the maximum amount of cubes."
  [input]
  (->> (input->data input)
       (map data->games-map)
       (map turns-power)
       (reduce +)
       ))

(comment
  (games-power-sum "day2_input.txt")
  (turns-power  {:game-id 1, :turns [{:blue 3, :red 4} {:red 1, :green 2, :blue 6} {:green 2}]})
  
  )