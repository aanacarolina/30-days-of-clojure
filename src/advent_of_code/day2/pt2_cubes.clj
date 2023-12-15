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

(def max-cubes {:red 12 :green 13 :blue 14})

#_(defn turns-vals
  [game]
  (   (map #(> 14 %) (flatten (map vals (game :turns))))))

(defn valid-turn? [turn]
  (let [{:keys [red green blue]
         :or {red 0 green 0 blue 0}} turn]
    (and 
     (>= 12 red)
     (>= 13 green)
     (>= 14 blue)
     ))) 

(defn valid-game? [game]
  (every? valid-turn? (:turns game)))

;----------puzzle output---------------

(defn possible-games-sum
  "Calculates the sum of game IDs that would have been possible, given the maximum amount of cubes."
  [input]
  (->> (input->data input)
       (map data->games-map)
       (filter valid-game?)
       (map :game-id)
       (reduce +)))

(comment
  (possible-games-sum "day2_exinput.txt")
)