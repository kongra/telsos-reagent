(ns telsos.ui.main
  (:require
   [reagent.core :as r]
   [telsos.ui.css :refer [twc twcs]]))

(def count-atom (r/atom 0))

(defn inc-count-atom [_]
  (r/rswap! count-atom inc))

(defn spacer-v-10 [text]
  [:div {:style {:height "20px"}} (str text)])

(def button-border-twc
  (twc ["border-rose-500/75" "border-4"]))

(def button-twc
  (twc ["bg-indigo-300" button-border-twc "px-8 py-4 w-[20rem]"]))

(defn button [text]
  [:button
   {:class (twcs button-twc)
    :on-click inc-count-atom} (str text " count: " @count-atom)])

(defn ui-main []
  [:div
   [button "Button 0"]
   [spacer-v-10 "123"]
   [:div.main "Hello World! " @count-atom]
   [button "Button 1"]
   [spacer-v-10 "456"]
   [button "Button 2"]])
