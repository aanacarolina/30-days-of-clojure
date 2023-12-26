(ns yr2023.day3.gear-ratios
  (:require [clojure.string :as str]))

;parse 
;split by digits juntos
;ponto
;anything que nao seja ponto e digit

;criar matriz/map
(def data-map '({:x 0, :y 0, :value "467", :type :digit}
                {:x 5, :y 0, :value "114", :type :digit}
                {:x 3, :y 1, :value "*", :type :symbol}
                {:x 2, :y 2, :value "35", :type :digit}
                {:x 6, :y 2, :value "633", :type :digit}
                {:x 6, :y 3, :value "#", :type :symbol}
                {:x 0, :y 4, :value "617", :type :digit}
                {:x 3, :y 4, :value "*", :type :symbol}
                {:x 5, :y 5, :value "+", :type :symbol}
                {:x 7, :y 5, :value "58", :type :digit}
                {:x 2, :y 6, :value "592", :type :digit}
                {:x 6, :y 7, :value "755", :type :digit}
                {:x 3, :y 8, :value "$", :type :symbol}
                {:x 5, :y 8, :value "*", :type :symbol}
                {:x 1, :y 9, :value "664", :type :digit}
                {:x 5, :y 9, :value "598", :type :digit}))

#_'([{:x 0, :y 0, :value 467 :type :number}
     {:x 3, :y 0, :value 114 :type :number}]
    [{:x 3, :y 1, :value "*" :type :symbol}]
    [{:x 0, :y 2, :value 35 :type :number}
     {:x 3, :y 2, :value 633 :type :number}]
    [{:x 2, :y 3, :value "#" :type :symbol}]
    [{:x 0, :y 4, :value 617 :type :number}
     {:x 1, :y 4, :value "*" :type :symbol}]
    [{:x 6, :y 5, :value "+" :type :symbol}
     {:x 7, :y 5, :value 58 :type :number}]
    [{:x 2, :y 6, :value 592 :type :number}]
    [{:x 6, :y 7, :value 755 :type :number}]
    [{:x 3, :y 8, :value 664 :type :number}
     {:x 4, :y 8, :value 598 :type :number}])

;story 
;x = line 
;y = column

;is-adjacent - 1st narrow down by line of symbol is +1/-1 in relation to the number

;filter by is-adjacent?

;sum total


(defn input->data [file]
  (str/split-lines (slurp file)))

;(+ x (count (re-find #"\d+" s)))
;(parse-long (str s (take-while #(Character/isDigit %) s))) 


(defn parse-data [y s]
  (loop [result []
         x 0]
    (cond
      ;checks if eol
      (>= x (count s))
      result
      ;checks if dot
      (= (subs s x (inc x)) ".")
      (recur
       result
       (inc x))
      ;checks if digit
      (-> s
          (.charAt x)
          Character/isDigit)
      (let [v (apply str (take-while #(Character/isDigit %) (subs s x)))]
        (recur
         (conj result
               {:x x
                :y y
                :value (parse-long v)
                :width (count v)
                :type :part})
         (+ x (count v))))
      ;checks if symbol (actually anything other than eol, digit or dot))
      :else
      (recur
       (conj result {:x x :y y :value (subs s x (inc x)) :type :symbol})
       (inc x)))))

(defn parts-list [data-list]
  
  (filter #(= (:type %) :part) data-list))

(defn symbols-list [data-list]
  (filter #(= (:type %) :symbol) data-list))

(defn adjacent-locations [x y width]
  (for [ adj-x (range (dec x) (+ x width 1))  
        adj-y (range (dec y) (+ y 2))];height is one and extra 1 not inclusive
    [adj-x adj-y])) 

;is there are better fast way
;giant non datomic database colunm birthdate - index date of birth and then check on this
;what that would look like for us here

;find a symbol at adjacent locations

(defn is-valid-part? [symbols-locations part]
  (let [{:keys [x y width]} part
        part-adj-locations (adjacent-locations x y width)]
     ;(some (fn [l](contains? symbols-locations l)) part-adj-locations)
    (some symbols-locations part-adj-locations)
    ))

;part x and y 
;neighboring x and y of symbols around the part width
;either sum or sub  [[x y][x y][x y][x y]]
;if in the map of symbols matches the x

(defn symbol-location [symbol]
  [(:x symbol) (:y symbol)])

(defn process-gears [input]
  (let [nodes (->> (input->data input)
                   (map-indexed parse-data)
                   (reduce concat))
        parts (parts-list nodes)
        symbols (set (map symbol-location (symbols-list nodes)))]
    (->> parts
         (filter #(is-valid-part? symbols %))
         (map :value)
         (reduce + ))
         ))

(comment

  (process-gears "day3_exinput.txt")
  (process-gears "day3_input.txt")
  (adjacent-locations 7 5 2)
  (parts-list data-map)
  (symbols-list data-map)

  (-> "467.+..114..."
      (.charAt 5)
      Character/isDigit)
   ;Same thing
  (number? (parse-long (subs "467.+..114..." 5 (inc 5))))
  )







