from entities.merchant import Merchant
from datastore.datastore_interface import DataStoreInterface


class MerchantUseCase:
    def __init__(self, db: DataStoreInterface):
        self._db = db
        self._merchant = Merchant

    def new_merchant(self, name: str, email: str, discount_percentage: str) -> dict:
        """
        Create new merchant
        Args:
            name: name of merchant
            email: email of merchant
            discount_percentage: discount percentage given by merchant

        Returns:
            dict: True if merchant created otherwise False
        """
        try:
            merchant = self._db.get_merchant(name)
            if merchant:
                result = {'success': True, 'message': f'Merchant "{merchant}" already exists'}

            else:
                merchant = self._merchant(name, email, discount_percentage)
                if merchant is not False:
                    self._db.add_merchant(merchant)
                    result = {'success': True, 'message': merchant}

                else:
                    result = {'success': False, 'message': 'Error while creating merchant'}

            return result

        except Exception as e:
            result = {'success': False, 'message': f'Error while creating merchant: {e}'}
            return result

    def update_merchant_discount_percentage(self, name: str, percentage: str) -> dict:
        """
        Update merchant discount percentage
        Args:
            name: name of merchant
            percentage: discount percentage given by merchant

        Returns:
            dict: True if percentage is updated otherwise False
        """
        try:
            if self._merchant.discount_percentage_validation(percentage):
                merchant = self._db.get_merchant(name)
                if merchant:
                    merchant['discount_percentage'] = percentage
                    self._db.update_merchant(name, merchant)
                    result = {'success': True, 'message': 'success!'}

                else:
                    result = {'success': False, 'message': f'Merchant "{name}" does not exists'}

            else:
                result = {'success': False, 'message': 'Discount Percentage is invalid!'}

            return result

        except Exception as e:
            result = {'success': False, 'message': f'Error while updating merchant discount percentage: {e}'}
            return result

    def report_discount(self, mrchnt: str) -> dict:
        """
        Report total discount received from merchant
        Args:
            mrchnt: name merchant

        Returns:
            dict: Total discount received from merchant otherwise False
        """
        try:
            merchant = self._db.get_merchant(mrchnt)
            if merchant:
                result = {'success': True, 'message': merchant['discount_received']}

            else:
                result = {'success': False, 'message': f'Merchant "{mrchnt}" not exists!'}

            return result

        except Exception as e:
            result = {'success': False, 'message': f'Error while getting report discount: {e}'}
            return result
