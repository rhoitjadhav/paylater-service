from entities.user import User
from datastore.datastore_interface import DataStoreInterface


class UserUseCase:
    def __init__(self, db: DataStoreInterface):
        self._db = db
        self._user = User

    def new_user(self, name: str, email: str, credit_limit: str) -> dict:
        """
        Create new user
        Args:
            name: name of user
            email: email of user
            credit_limit: credit limit for user

        Returns:
            dict: True if user created otherwise False for exception
        """
        try:
            user = self._db.get_user(name)
            if user:
                result = {'success': False, 'message': f'User "{name}" already exists'}

            else:
                user = self._user(name, email, credit_limit)
                if user:
                    self._db.add_user(user)
                    result = {'success': True, 'message': user}

                else:
                    result = {'success': False, 'message': 'Error while creating user'}

            return result
        except Exception as e:
            return {'success': False, 'message': f'Error while creating user: {e}'}

    def transaction(self, usr: str, mrchnt: str, amt: str) -> dict:
        """
        Carry out transaction for user
        Args:
            usr: name of user
            mrchnt: name of merchant
            amt: amount to be used for transaction

        Returns:
            dict: True if transaction success full otherwise False
        """
        try:
            amount = int(amt)

            user = self._db.get_user(usr)
            if user is False:
                result = {'success': False, 'message': f'User "{usr}" not exists'}
                return result

            merchant = self._db.get_merchant(mrchnt)
            if merchant is False:
                result = {'success': False, 'message': f'Merchant "{mrchnt}" not exists'}
                return result

            if user['current_limit'] < amount:
                result = {'success': False, 'message': 'rejected! (reason: credit limit)'}

            else:
                # Update Current Limit
                user['current_limit'] -= amount
                self._db.update_user(usr, user)

                # Update Discount Received
                merchant['discount_received'] += merchant['discount_percentage'] * 0.01 * amount
                self._db.update_merchant(mrchnt, merchant)

                result = {'success': True, 'message': 'success!'}

            return result

        except Exception as e:
            return {'success': False, 'message': f'Error while executing transaction: {e}'}

    def payback_dues(self, usr: str, amt: str) -> dict:
        """
        Payback dues of user
        Args:
            usr: name user
            amt: amount to be paid back

        Returns:
            dict: True if payback successful otherwise False
        """
        try:
            amount = int(amt)
            user = self._db.get_user(usr)
            if user:
                user['current_limit'] += amount
                dues = user['credit_limit'] - user['current_limit']
                if dues < 0:
                    result = {'success': False, 'message': f'User "{usr}" is trying to paid more dues'}

                else:
                    self._db.update_user(usr, user)
                    result = {'success': True, 'message': f'{usr}(dues:{dues})'}

            else:
                result = {'success': False, 'message': f'User "{usr}" not exists!'}

            return result

        except Exception as e:
            result = {'success': False, 'message': f'Error at payback dues: {e}'}
            return result

    def report_total_dues(self) -> dict:
        """
        Reports total dues of users
        Returns:
            dict: True and list the users with dues otherwise False for exception
        """
        try:
            users = self._db.get_users()
            total = 0
            message = ''
            for user in users:
                name = users[user]['name']
                dues = users[user]['credit_limit'] - users[user]['current_limit']
                if dues != 0:
                    total += dues
                    message += f'{name}: {dues}\n'

            message += f'total: {total}'
            result = {'success': True, 'message': ''}

            return result

        except Exception as e:
            result = {'success': False, 'message': f'Error while getting report of total dues: {e}'}
            return result

    def report_users_at_credit_limit(self) -> dict:
        """
        Report list of users who has `0` current credit limit

        Returns:
            dict: List of users with `0` current credit limit
        """
        try:
            users = self._db.get_users()
            message = ''
            for user in users:
                if users[user]['current_limit'] == 0:
                    message += users[user]['name']

            result = {'success': True, 'message': message}
            return result

        except Exception as e:
            result = {'success': False, 'message': f'Error while getting report of users at credit limit: {e}'}
            return result

    def report_dues(self, usr: str) -> dict:
        """
        Report dues of an user
        Args:
            usr: name of user

        Returns:
            dict: total dues of an user
        """
        try:
            user = self._db.get_user(usr)
            if user:
                dues = user['credit_limit'] - user['current_limit']
                result = {'success': True, 'message': f'dues: {dues}'}

            else:
                result = {'success': False, 'message': f'User "{usr}" not exists!'}

            return result

        except Exception as e:
            result = {'success': False, 'message': f'Error while getting report dues: {e}'}
            return result
