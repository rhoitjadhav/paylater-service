class User:
    def __init__(self, name, email, credit_limit):
        self._name = name
        self._email = email
        self._credit_limit = int(credit_limit)
        self._current_limit = self._credit_limit

    def __new__(cls, *args, **kwargs):
        try:
            name, email, credit_limit = args[0], args[1], args[2]

            # Entity Validation
            if cls.name_validation(name) and cls.email_validation(email) and cls.credit_limit_validation(credit_limit):
                # Instance Creation
                instance = super().__new__(cls)
                return instance
            else:
                return False

        except Exception as e:
            print('Error at __new__:', e)
            return False

    def __repr__(self):
        return f'{self._name}({self._credit_limit})'

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
    def credit_limit_validation(credit_limit):
        try:
            int(credit_limit)
            return True
        except Exception as e:
            print(e)
            return False

    @property
    def name(self):
        return self._name

    @property
    def email(self):
        return self._email

    @property
    def credit_limit(self):
        return self._credit_limit

    @property
    def current_limit(self):
        return self._current_limit
