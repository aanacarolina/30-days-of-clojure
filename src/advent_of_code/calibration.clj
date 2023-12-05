(ns advent-of-code.calibration
  (:require [clojure.string :as str]))

(defn atoi [string]
  ( str/replace string #"\D" ""))

(defn str->two-digits [digit-str]
 (str (subs digit-str 0 1) (subs digit-str (dec (count digit-str)))))

(defn calibration
  [input]
  (reduce + 
          (map parse-long 
               (map str->two-digits 
                    (map atoi (str/split-lines (slurp input)))))))

;ingest input
;read each line separetedly , vector of strings
;identify numbers
;identify first and last numbers
;if I only have one number repeat it once 
;;if more than 2 digits consider first last
;return the 2-digit final number
;create a collection of all input 2-digit numbers
;reduce to a final sum 

(comment
  (calibration "full_input.txt")
  (atoi "1abc2")
  (str->two-digits "38")
  )