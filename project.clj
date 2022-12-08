(defproject retemp "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure          "1.9.0"]
                 [org.clojure/clojurescript "1.10.439"]
                 [org.clojure/core.async     "1.5.648"]
                 [reagent                      "0.8.1"]
                 ;; [cljsjs/react           "17.0.2-0"]
                 ;; [cljsjs/react-dom       "17.0.2-0"]
                 ]

  :plugins      [[lein-cljsbuild             "1.1.7"]
                 [lein-figwheel             "0.5.17"]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :cljsbuild
  {:builds
   [{:id "dev"
     :source-paths ["src"]
     :figwheel {:on-jsload            "retemp.core/on-js-reload"}
     :compiler {:main                 retemp.core
                :asset-path           "js/compiled/out"
                :output-to            "resources/public/js/compiled/retemp.js"
                :output-dir           "resources/public/js/compiled/out"
                :source-map-timestamp true }}

    {:id "min"
     :source-paths ["src"]
     :compiler {:output-to            "resources/public/js/compiled/retemp.js"
                :main                  retemp.core
                :optimizations         :advanced
                :pretty-print          false }}]}

  :figwheel {:css-dirs ["resources/public/css"]})
