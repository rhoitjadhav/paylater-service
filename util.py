import inspect


class Util:
    @staticmethod
    def check_no_of_args(count):
        def decorator(func):
            def wrapper(*args):
                if count != len(args):
                    return {
                        'success': False,
                        'message': 'Missing arguments!'
                    }
                return func(*args)

            return wrapper

        return decorator

    @staticmethod
    def singleton(class_):
        instances = {}

        def get_instance(*args, **kwargs):
            if class_ not in instances:
                instances[class_] = class_(*args, **kwargs)
            return instances[class_]

        return get_instance

    @staticmethod
    def get_function_params_list(func):
        return len(inspect.signature(func).parameters)
