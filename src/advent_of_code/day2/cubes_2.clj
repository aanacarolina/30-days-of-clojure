(ns advent-of-code.day2.cubes_2
  (:require [clojure.string :as str]))

(def max-cubes {:red 12 :green 13 :blue 14})

(defn input->data [input]
  (str/split-lines (slurp input)))

(defn parse-turns [turns]
  (str/split turns #"\s*;\s*"))

(defn parse-take [turn string]
  (let [[_ v k] (re-matches #"(\d+) (red|green|blue)" string)]
    (assoc turn (keyword k) (parse-long v))))

(defn parse-comma [turn]
  (str/split turn #"\s*,\s*"))

(defn parse-turn [string]
  (->> (parse-turns string)
       (map parse-comma)
       (map #(reduce parse-take {} %))
       (into [])
       ))

 (defn data->games-map [game-info]
  (let [[_ k v] (re-matches #"Game (\d+): (.*)" game-info)]
    {:game (parse-long k) :turns (parse-turn v)}))

(defn calculate-cubes [input]
  (->> (input->data input)
       (map data->games-map)
      ))

(comment
  (calculate-cubes "day2_exinput.txt")

  (input->data "day2_exinput.txt")
  (map data->games-map ["Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
                        "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue"
                        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"
                        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red"
                        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"])
  
    
  (reduce parse-take {} ["1 blue" "2 green"])
  ;same as (parse-take (parse-take {} "1 blue") "2 green")
  ;=> {:blue 1, :green 2}
  (parse-turn "1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue")
  ;(["1 blue" "2 green"] ["3 green" "4 blue" "1 red"] ["1 green" "1 blue"])

  (map data->games-map (input->data "day2_exinput.txt"))
  )

;builds on top of parse turn 
#_("3 red , 2 blue; 1 green, 2 red"
["3 red, 2 blue" "1 green, 2 red"]
[{:red 3 :blue 2} {:green 1 :red 2}])
;outputs a map for each turn
;"Game 1: 2 blue, 1 red; 1 green, 2 blue"


#_(game -> split on ; => turn strings
        turn string -'> split on , => take strings
        take string -> parse count and color, update map)

 