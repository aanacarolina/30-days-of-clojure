(ns advent-of-code.calibration-p2
  (:require [clojure.string :as str]))



(def nbr {"eno" "1" "owt" "2" "eerht" "3" "ruof" "4" "evif" "5" "xis" "6" "neves" "7" "thgie" "8" "enin" "9"})

(defn str->number [string]
  (str/replace string #"eno|owt|eerht|ruof|evif|xis|neves|thgie|enin"  #(str (get nbr %))))

(defn atoi [string]
  (str/replace string #"\D" ""))

(defn str->two-digits [digit-str]
  (str (subs digit-str 0 1) (subs digit-str (dec (count digit-str)))))

(defn calibration-two
  [input]
  (reduce + 
          (map parse-long 
               (map str->two-digits 
                    (map atoi 
                        (map #(apply str (str/reverse %))  
                             (map str->number 
                                  (map #(apply str (str/reverse %)) (str/split-lines (slurp input))))))))))


 
;find spelled number in strings
;change for digit value 

(comment
  (calibration-two "full_input.txt")
  (calibration-two "input.txt")
  (apply str (reverse "2ninexblcgmhxxceightwop"))
  (str->number "powthgiecxxhmgclbxenin2")
  (apply str (reverse "p2hgiecxxhmgclbx92"))
  (atoi "29xblcgmhxxceigh2p")
  (str->two-digits "292")
  (parse-long "22")

  )