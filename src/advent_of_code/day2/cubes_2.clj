(ns advent-of-code.day2.cubes_2
  (:require [clojure.string :as str]))

;--------------- input parser ----------------
#_(glossary
   game - map of key value pair - being => k :game-id v :turns -> {:game-id 1, :turns [{:blue 3, :red 4} {:red 1, :green 2, :blue 6} {:green 2}]}
   game-id - key value pair - being => k :game v game id -> :game 3
   turns - vector of maps containing a turn -> [{:colour int} {:colour int}]
   turn - map of takes -> {:green 5, :red 1}
   take - key value pair - being => k :colour v qty -> :green 3)

(defn input->data
  "reads input (one big string) and based on line breaks splits into smallers strings (this string is a game and its turns)"
  [input]
  (str/split-lines (slurp input)))

(defn split-turns
  "receveis a  "
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

;



;----------puzzle output---------------

(defn possible-games-sum
  "Calculates the sum of game IDs that would have been possible, given the maximum amount of cubes."
  [input]
  (->> (input->data input)
       (map data->games-map)))

(comment
  (possible-games-sum "day2_exinput.txt")

  (input->data "day2_exinput.txt")
  (map data->games-map ["Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
                        "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue"
                        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"
                        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red"
                        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"])

  (parse-take {} "1 blue")
  (reduce parse-take {} ["1 blue" "2 green"])
  ;same as (parse-take (parse-take {} "1 blue") "2 green")
  ;=> {:blue 1, :green 2}
  (parse-turns "1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue")
  ;(["1 blue" "2 green"] ["3 green" "4 blue" "1 red"] ["1 green" "1 blue"])

  (map data->games-map (input->data "day2_exinput.txt")))

;builds on top of parse turn 
#_("3 red , 2 blue; 1 green, 2 red"
   ["3 red, 2 blue" "1 green, 2 red"]
   [{:red 3 :blue 2} {:green 1 :red 2}])
;outputs a map for each turn
;"Game 1: 2 blue, 1 red; 1 green, 2 blue"
#_(game -> split on ; => turn strings
        turn string -'> split on , => take strings
        take string -> parse count and color, update map)

 