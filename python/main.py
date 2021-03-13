from controller.main_controller import MainController


class Main:
    def __init__(self):
        self._main_controller = MainController()

    def run(self):
        try:
            while True:
                commands = input('> ').split()
                if len(commands) == 0:
                    print('Please enter command')
                    continue

                if len(commands) < 2:
                    print('Please valid command')
                    continue

                response = self._main_controller.handler(*commands)
                print(response['message'])

        except KeyboardInterrupt:
            print('Keyboard Interrupt')


if __name__ == '__main__':
    main = Main()
    main.run()
