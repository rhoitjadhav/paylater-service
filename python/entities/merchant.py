class Merchant:
    def __init__(self, name, email, discount_percentage):
        self._name = name
        self._email = email
        self._discount_percentage = float(discount_percentage.split("%")[0])
        self._discount_received = 0

    def __new__(cls, *args, **kwargs):
        try:
            name, email, discount_percentage = args[0], args[1], args[2]

            # Entity Validation
            if cls.name_validation(name) and cls.email_validation(email) and cls.discount_percentage_validation(
                    discount_percentage):
                # Instance Creation
                instance = super().__new__(cls)
                return instance
            else:
                return False

        except Exception as e:
            print('Error while creating merchant instance:', e)
            return False

    def __repr__(self):
        return f'{self._name}({self._discount_percentage}%)'

    @staticmethod
    def name_validation(name):
        try:
            if not isinstance(name, str):
                raise TypeError(f'`name` should be string instead found {type(name).__name__}')

            return True
        except Exception as e:
            print(e)
            return False

    @staticmethod
    def email_validation(email):
        try:
            if not isinstance(email, str):
                raise TypeError(f'`email` should be string instead found {type(email).__name__}')

            return True
        except Exception as e:
            print(e)
            return False

    @staticmethod
    def discount_percentage_validation(discount_percentage):
        try:
            if "%" not in discount_percentage:
                raise ValueError(
                    f'`%` sign not found in input, instead found {discount_percentage}')

            return True
        except Exception as e:
            # print(e)
            return False

    def set_discount_percentage(self, percentage):
        self._discount_percentage = percentage

    @property
    def name(self):
        return self._name

    @property
    def email(self):
        return self._email

    @property
    def discount_percentage(self):
        return self._discount_percentage

    @property
    def discount_received(self):
        return self._discount_received
