import unittest
from usecases.user_usecase import UserUseCase
from usecases.merchant_usecase import MerchantUseCase
from datastore.local_datastore import LocalDataStore


class TestUser(unittest.TestCase):
    _user_use_case = UserUseCase(LocalDataStore())
    _merchant_use_case = MerchantUseCase(LocalDataStore())

    def setUp(self):
        self._merchant_use_case.new_merchant('m1', 'm1@email.com', '0.5%')
        self._merchant_use_case.new_merchant('m2', 'm2@email.com', '1.5%')
        self._merchant_use_case.new_merchant('m3', 'm3@email.com', '1.25%')

    def test_1(self):
        """New User"""
        tests = [
            ('user1', 'user1@users.com', '300', True),
            ('user1', 'user2@users.com', '400', False),
            ('user2', 'user2@users.com', '400', True),
            ('user3', 'user3@users.com', '500', True)
        ]

        for test in tests:
            result = self._user_use_case.new_user(test[0], test[1], test[2])
            self.assertEqual(result['success'], test[3])

    def test_2(self):
        """Transaction"""
        tests = [
            ('user2', 'm1', '500', False),
            ('user1', 'm2', '300', True),
        ]
        for test in tests:
            result = self._user_use_case.transaction(test[0], test[1], test[2])
            self.assertEqual(result['success'], test[3])

    def test_3(self):
        """Payback Dues"""
        tests = [
            ('user1', '200', True),
            ('user1', '200', False)
        ]

        for test in tests:
            result = self._user_use_case.payback_dues(test[0], test[1])
            self.assertEqual(result['success'], test[2])

    def test_4(self):
        """Report Total Dues"""
        result = self._user_use_case.report_total_dues()
        self.assertEqual(result['success'], True)

    def test_5(self):
        """Report Users at Credit Limit"""
        result = self._user_use_case.report_users_at_credit_limit()
        self.assertEqual(result['success'], True)

    def test_6(self):
        """Report Dues"""
        tests = [
            ('user1', True),
            ('user4', False)
        ]
        for test in tests:
            result = self._user_use_case.report_dues(test[0])
            self.assertEqual(result['success'], test[1])
