from util import Util
from datastore.datastore_interface import DataStoreInterface


@Util.singleton
class LocalDataStore(DataStoreInterface):
    def __init__(self):
        self._user_list = {}
        self._merchant_list = {}

    def get_user(self, name):
        return self._user_list.get(name, False)

    def get_users(self):
        return self._user_list

    def get_merchant(self, name):
        return self._merchant_list.get(name, False)

    def get_merchants(self):
        return self._merchant_list

    def add_user(self, user):
        name = user.name
        email = user.email
        credit_limit = user.credit_limit
        current_limit = user.current_limit

        self._user_list[name] = {
            'name': name,
            'email': email,
            'credit_limit': credit_limit,
            'current_limit': current_limit
        }

    def add_merchant(self, merchant):
        name = merchant.name
        email = merchant.email
        discount_percentage = merchant.discount_percentage
        discount_received = merchant.discount_received

        self._merchant_list[name] = {
            'name': name,
            'email': email,
            'discount_percentage': discount_percentage,
            'discount_received': discount_received
        }

    def update_user(self, name, user):
        self._user_list[name] = user

    def update_merchant(self, name, merchant):
        self._merchant_list[name] = merchant
