;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2016-03-07

(ns ^:figwheel-always retemp.core
    (:require [reagent.core :as r]))

(enable-console-print!)

;; VIEW

(defn hidbutton
  [label]
  (let [hidden? (r/atom false)]
    (fn []
      (when-not @hidden?
        [:button.btn.btn-warning
         {:on-click #(reset! hidden? true) :type "button"} label]))))

(def ^:private counter (r/atom 0))

(defn main-view []
  [:div.container
   [:h2 (str "Kliknąłeś " @counter " razy.")]
   [:button.btn.btn-success {:on-click #(swap!  counter inc) :type "button"} "Zwiększ"]
   [:button.btn.btn-danger  {:on-click #(reset! counter 0  ) :type "button"} "Zeruj"  ]

   [hidbutton "Schowaj mnie!!!"]
   [hidbutton "Nieee !!!"]])

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
