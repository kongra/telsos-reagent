(ns telsos.fe.main
  (:require
   [reagent.core :as r]))

(def count-atom (r/atom 0))

(defn inc-count-atom [_]
  (println "inc-count-atom")
  (r/rswap! count-atom inc))

(defn ui-spacer-v-10 [text]
  (println "ui-spacer-v-10" :text text)
  [:div {:style {:height "20px"}} (str text)])

(defn ui-button [text]
  (println "ui-button" :text text)
  [:button {:on-click inc-count-atom} (str text " count: " @count-atom)])

(defn ui-main []
  (println "Hello telsos.fe!")
  [:div
   [ui-button "Button 0"]
   [ui-spacer-v-10 "123"]
   [:div.main "Hello telsos.fe! " @count-atom]
   [ui-button "Button 1"]
   [ui-spacer-v-10 "456"]
   [ui-button "Button 2"]])
