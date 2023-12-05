(ns advent-of-code.calibration-p2
  (:require [clojure.string :as str]))

(def nbr {"one" "1" "two" "2" "three" "3" "four" "4" "five" "5" "six" "6" "seven" "7" "eight" "8" "nine" "9"})

(defn atoi [string]
  (str/replace string #"\D" ""))

(defn str->number [string]
  (str/replace string #"one|two|three|four|five|six|seven|eight|nine"  #(str (get nbr %))))

(defn str->two-digits [digit-str]
  (str (subs digit-str 0 1) (subs digit-str (dec (count digit-str)))))

(defn calibration
  [input]
  (->> (slurp input)
       str/split-lines
       (map str->number)
       (map atoi)
       (map str->two-digits)
       (map parse-long)
       (reduce +)) 
  )

;find spelled number in strings
;change for digit value 

(comment
  (calibration "full_input.txt")
  (calibration "input.txt")
  (atoi "1abc2")
  (str->two-digits "38")
  (str->two-digits "abc123xyz")
  (str->number "abcone2threexyz"))