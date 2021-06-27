# import requests
# from bs4 import BeautifulSoup


# def main():
#     s = "taipei 32!"
#     url="https://www.cwb.gov.tw/V8/C/W/OBS_Map.html"
#     r = requests.urlopen(url)
#     text = r.read()
#     print(text)
#     soup = BeautifulSoup(text, 'html.parser')
#     content = soup.find(id="TableId3hr")
#     print(content)
#     return text
# main()


# import urllib3
# url = "https://www.cwb.gov.tw/V8/C/W/OBS_Map.html" # The website url you want to access
# response = urllib3.urlopen(url)
# data = response.read()
# text = data.decode('utf-8-sig')
# print(response)



import urllib.request
dataid="F-C0032-001"
apikey="CWB-0F2980DC-85D7-42D3-8CAE-B60552697285"
format="JSON"
req = urllib.request.Request("https://opendata.cwb.gov.tw/fileapi/v1/opendataapi/"+dataid+"?Authorization="+apikey+"&format="+format)
with urllib.request.urlopen(req) as response:
    the_page = response.read()
print(the_page.decode("utf-8") )



