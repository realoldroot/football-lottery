import requests, json

base_url = 'http://192.168.0.120:8080'


def login():
    url = base_url + '/system/login'
    # 密码明文是123123
    data = {'username': '17600116321', 'password': '123456'}

    r = requests.post(url, json=data)

    print(r.status_code)
    print(r.text)


def currentTeam():
    url = base_url + '/lottery/next'
    headers = {'username': '17600116321', 'timestamp': '1526526195294',
               'sign': 'baa6f60067ba0fc4a552ebec11eded5084697fddc9fca5ce4faade3ee9c12adf'}
    r = requests.get(url, headers=headers)
    print(r.status_code)
    print(r.text)


def sms():
    url = base_url + '/system/sms'
    data = {'username': '18310860399'}
    r = requests.post(url, json=data)
    print(r.status_code)
    print(r.text)


def query():
    url = base_url + '/lottery/query'
    data = {'no': 2018050910, 'username': '18310860399'}
    r = requests.post(url, json=data)
    print(r.status_code)
    print(r.text)


def user_info():
    url = base_url + '/user/info'
    data = {'username', '17600116321'}
    r = requests.post(url, json=data)
    print(r.status_code)
    print(r.text)


if __name__ == '__main__':
    user_info()
