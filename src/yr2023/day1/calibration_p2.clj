(ns yr2023.day1.calibration-p2
  (:require [clojure.string :as str]))

(def nbr {"one" "1" 
          "two" "2"
          "three" "3" 
          "four" "4" 
          "five" "5" 
          "six" "6" 
          "seven" "7" 
          "eight" "8" 
          "nine" "9"})

(def rev-nbr {"eno" "1" 
              "owt" "2"
              "eerht" "3" 
              "ruof" "4" 
              "evif" "5" 
              "xis" "6" 
              "neves" "7" 
              "thgie" "8" 
              "enin" "9"})

(defn str->number [string]
  (str/replace string #"one|two|three|four|five|six|seven|eight|nine" #(str (get nbr %))))

(defn rev-str->number [string]
  (str/replace string #"eno|owt|eerht|ruof|evif|xis|neves|thgie|enin" #(str (get rev-nbr %))))

(defn get-first-digit [string]
 (first (re-seq #"one|two|three|four|five|six|seven|eight|nine|[0-9]" string)))

(defn get-last-digit [string]
  (first (re-seq #"eno|owt|eerht|ruof|evif|xis|neves|thgie|enin|[0-9]" (apply str (reverse string)))))

(defn join-numbers [string]
  (let [first-digit (str->number (get-first-digit string))
        last-digit (rev-str->number (get-last-digit string))]
    (str first-digit last-digit)))

(defn calibration-two
  [input]
  (->>
   (slurp input)
   (str/split-lines)
   (map join-numbers) 
   (map parse-long)
   (reduce +)))



;find spelled number in strings
;change for digit value 
;ansers tried: 54853 54913 54871
(comment
  (calibration-two "day1_input.txt")
  (calibration-two "day1_exinput.txt") 
  (parse-long "24")
  (get-first-digit "2ninexblcgmhxxceightwop")
  (get-last-digit "2ninexblcgmhxxceightwop")
  (join-numbers "2ninexblcgmhxxceightwop")
  (rev-str->number (get-last-digit "2ninexblcgmhxxceightwop"))
  (str->number "eightwo"))

