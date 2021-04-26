from abc import ABC, abstractmethod


class DataStoreInterface(ABC):

    @abstractmethod
    def get_user(self, name):
        pass

    @abstractmethod
    def get_users(self):
        pass

    @abstractmethod
    def get_merchant(self, name):
        pass

    @abstractmethod
    def get_merchants(self):
        pass

    @abstractmethod
    def add_user(self, user):
        pass

    @abstractmethod
    def add_merchant(self, merchant):
        pass

    @abstractmethod
    def update_user(self, name, user):
        pass

    @abstractmethod
    def update_merchant(self, name, merchant):
        pass
