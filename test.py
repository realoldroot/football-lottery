import requests, json

base_url = 'http://localhost:9000'


def login():
    url = base_url + '/system/login'
    # 密码明文是123123
    password = 'o+oON0JsPkWeSOhGIR582K9VMkyoQLZdlyl+Q5R0NNqXTembATWl5h5fRiyq2By5GPeV+BaNBT/mbFAB9i/MDEsjDt2ae30HHTmoJz0pk7/rgbm9QQ82z+WCcs4m01TGxtz7zXdVV8B+SeHXsDEbvHVhifnPIHYWI5EbeP64Cwk='
    data = {'username': '18310860399', 'password': password}

    r = requests.post(url, json=data)

    print(r.status_code)
    print(r.text)


def currentTeam():
    url = base_url + '/lottery/next'
    r = requests.get(url)
    print(r.status_code)
    print(r.text)


if __name__ == '__main__':
    login()
