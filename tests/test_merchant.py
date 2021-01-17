import unittest
from usecases.merchant_usecase import MerchantUseCase
from datastore.local_datastore import LocalDataStore


class TestMerchant(unittest.TestCase):
    _merchant_use_case = MerchantUseCase(LocalDataStore())

    def test_1(self):
        """New Merchant"""
        tests = [
            ('m1', 'm1@email.com', '1.5%', True),
            ('m2', 'm2@email.com', '1', False)
        ]

        for test in tests:
            result = self._merchant_use_case.new_merchant(test[0], test[1], test[2])
            self.assertEqual(result['success'], test[3])

    def test_2(self):
        """Update Merchant Discount Percentage"""
        tests = [
            ('m1', '1%', True),
            ('m2', '1', False)
        ]
        for test in tests:
            result = self._merchant_use_case.update_merchant_discount_percentage(test[0], test[1])
            self.assertEqual(result['success'], test[2])

    def test_3(self):
        """Report Discount"""
        tests = [
            ('m1', True),
            ('m2', False)
        ]
        for test in tests:
            result = self._merchant_use_case.report_discount(test[0])
            self.assertEqual(result['success'], test[1])
