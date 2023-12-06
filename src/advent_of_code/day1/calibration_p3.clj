(ns advent-of-code.day1.calibration-p3
  (:require [clojure.string :as str]))



(def nbr {"one" "on1e" 
          "two" "t2o"
          "twone" "t2o1e"
          "eightwo" "e8t2o"   
          "three" "t3e" 
          "four" "f4r" 
          "five" "f5e" 
          "six" "s6x" 
          "seven" "s7n" 
          "eight" "e8t" 
          "ight" "eh8t" 
          "nine" "n9e"})
;oneight
(defn str->number [string]
  (str/replace string #"one|twone|two|eightwo|three|four|five|six|seven|eight|ight|nine" #(str (get nbr %))))

(defn atoi [string]
  (str/replace string #"\D" ""))

(defn str->two-digits [digit-str]
  (str (subs digit-str 0 1) (subs digit-str (dec (count digit-str)))))

(defn calibration-three
  [input]
  (->>
   (slurp input)
   (str/split-lines)
   (map str->number)
   (map atoi)
   (map str->two-digits)
   (map parse-long)
   (reduce +)))



;find spelled number in strings
;change for digit value 
;ansers tried: 54853 54913 54871
(comment
  (calibration-three "full_input.txt")
  (calibration-three "input.txt")
  (apply str (reverse "2ninexblcgmhxxceightwop"))
  (str->number "sevenineight")
  (atoi "e8tt2ot3e9247t2oe8t2oxsq") 
  (str->two-digits "2418984")
  (parse-long "24"))

