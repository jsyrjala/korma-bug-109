(ns lobos.migrations
  (:refer-clojure :exclude [drop bigint boolean char double float time])
  (:use [lobos connectivity]
        [lobos.schema :only (table integer varchar)]
        [lobos.core :only (create drop)]
        [lobos.migration :only (defmigration)]
        )
  )

(defmigration add-users-table
  (up [] (create (table :users
                        (integer :id :auto-inc :primary-key)
                        (varchar :name 100 :unique))))
  (down [] (drop (table :users))))