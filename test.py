import requests, json

base_url = 'http://localhost:8080'


def login():
    url = base_url + '/system/login'
    data = {'username': '12345678', 'password': '1233'}

    r = requests.post(url, json=data)

    print(r.status_code)
    print(r.text)


if __name__ == '__main__':
    login()
