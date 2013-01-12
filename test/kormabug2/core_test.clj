(ns kormabug2.core-test
  (:use midje.sweet)
  (:use [lobos connectivity]
        [lobos.core :only (migrate)]
        [korma.core ]
        [korma.db :only (defdb postgres)])
  )

;; does same as korma.db.h2, but it is not available in every Korma version
(defn h2-func
  [{:keys [db] :as opts}]
  (let [db (or (:db opts) "h2.db")]
    (merge {:classname "org.h2.Driver" ; must be in classpath
            :subprotocol "h2"
            :subname db}
           opts)))

(defn h2-conf[]
  (def dbconf {:classname "org.h2.Driver"
               :subprotocol "h2"
               ;; DB_CLOSE_DELAY keeps db around until JVM
               ;; shuts down
               :subname "mem:integration_tests;DB_CLOSE_DELAY=-1"
               :user "sa"
               :password ""             
               })
  (open-global dbconf)
  (defdb db (h2-func dbconf))
  )


(defn postgres-conf []
  (def dbconf {:classname "org.postgresql.Driver"
               :subprotocol "postgresql"
               :user "ruuvi"
               :password "ruuvi"
               :subname "//localhost/ruuvi_server"
               })
    (open-global dbconf)
    (defdb db (postgres dbconf))
  )

;; call here either (postgres-conf) or (h2-conf)

(h2-conf)
;; (postgres-conf)

(migrate)


(defentity users
  (table :users)
  (pk :id)
  (entity-fields :name)
  )

(def result (insert users (values {:name "test"})))
;; with korma 0.3.0-beta14, {:scope_entity() 1}
;; with korma 0.3.0-beta13, {:SCOPE_IDENTITY() 1}
;; with postgres {:id 1 :name "test"}
(prn "Result:")
(prn "result" result)