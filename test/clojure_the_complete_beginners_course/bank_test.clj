(ns clojure-the-complete-beginners-course.bank-test
  (:require [clojure.test :refer :all]
            [clojure-the-complete-beginners-course.bank :refer :all])
  )

(deftest bank-test
  (clean-accounts)
  (testing "create-account"
    (testing "Creates an account with the specified name"
      (is (= (create-account "new-account" 10) "new-account"))
      )
    (testing "Fails if account already exists"
      (create-account "duplicated-account" 10)
      (is (= (create-account "duplicated-account" 10) "account-already-exists"))
      )
    )
  (testing "transaction"
    (testing "Fails if src account does not exists"
      (create-account "dst-existing-account" 5)
      (is (= (transaction "ups-account" "dst-existing-account" 0) "src-account-not-exists"))
      )
    (testing "Fails if dst account does not exists"
      (create-account "src-existing-account" 5)
      (is (= (transaction "src-existing-account" "ups-account" 0) "dst-account-not-exists"))
      )
    (testing "Transfers amount between accounts"
      (create-account "account-1" 10)
      (create-account "account-2" 0)
      (is (= (transaction "account-1" "account-2" 5) "transferred"))
      (is (= (:amount (get-account "account-1")) 5))
      (is (= (:amount (get-account "account-2")) 5))
      )
    (testing "Fails if src account does not have enough money"
      (create-account "account-3" 5)
      (create-account "account-4" 0)
      (is (= (transaction "account-3" "account-4" 15) "not-enough-money-in-src-account"))
      (is (= (:amount (get-account "account-3")) 5))
      (is (= (:amount (get-account "account-4")) 0))
      )
    )
  )
