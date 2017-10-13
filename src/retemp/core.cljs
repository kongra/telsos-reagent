;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2016-03-07

(ns ^:figwheel-always retemp.core
  (:require [reagent.core    :as     r]
            [cljs.core.async :refer [<! >! put! chan timeout]])

  (:require-macros [cljs.core.async.macros :refer [go alt! go-loop]]))

(enable-console-print!)

;; VIEW

(defn hidbutton
  [label]
  (let [isHidden (r/atom false)]
    (fn []
      (when-not @isHidden
        [:button.btn.btn-warning
         {:on-click #(reset! isHidden true) :type "button"} label]))))

(def ^:private counter (r/atom 0))

(defn main-view []
  [:div.container
   [:h2 (str "You've clicked " @counter " times.")]
   [:button.btn.btn-success {:on-click #(swap!  counter inc) :type "button"} "Increase"]
   [:button.btn.btn-danger  {:on-click #(reset! counter   0) :type "button"}     "Zero"]
   [hidbutton "Hide me !!!"]
   [hidbutton   "Noooo !!!"]])

;; INSTRUMENTATION

(defn on-js-reload
  []
  (println "Reloaded...")
  (r/render-component [main-view] (. js/document (getElementById "app"))))

(defn init
  []
  (on-js-reload))

(defonce start
  (init))
