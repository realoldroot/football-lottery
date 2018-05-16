import requests, json

base_url = 'http://localhost:8080'


def login():
    url = base_url + '/system/login'
    # 密码明文是123123
    data = {'username': '17600116321', 'password': '123456'}

    r = requests.post(url, json=data)

    print(r.status_code)
    print(r.text)


def currentTeam():
    url = base_url + '/lottery/next'
    r = requests.get(url)
    print(r.status_code)
    print(r.text)


def sms():
    url = base_url + '/system/sms'
    data = {'username': '18310860399'}
    r = requests.post(url, json=data)
    print(r.status_code)
    print(r.text)


if __name__ == '__main__':
    login()
