(defproject kormabug109 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :min-lein-version "2.0.0"
  :plugins [[lein-ring "0.7.5"]
            [lein-midje "2.0.4"]
            ]
  
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [korma "0.3.0-beta14"]
                 [lobos "1.0.0-beta1"]
                 [midje "1.4.0"]

                 [com.h2database/h2 "1.3.168"]
                 [postgresql/postgresql "9.1-901.jdbc4"]
                 ])

