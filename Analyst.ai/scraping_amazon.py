import pandas as pd
from bs4 import BeautifulSoup
import numpy as np
import requests


def get_title(soup):
  
  try: 
    title= soup.find("span",attrs={'id':'productTitle'})

    title_value= title.text

    title_string = title_value.strip()
  except AttributeError:
    title_string=""
  return title_string

def get_price(soup):

    try:
        price = soup.find("span", attrs={'id':'priceblock_ourprice'}).string.strip()

    except AttributeError:

        try:
            # If there is some deal price
            price = soup.find("span", attrs={'id':'priceblock_dealprice'}).string.strip()

        except:
            price = ""

    return price

def get_rating(soup):

    try:
        rating = soup.find("i", attrs={'class':'a-icon a-icon-star a-star-4-5'}).string.strip()
    
    except AttributeError:
        try:
            rating = soup.find("span", attrs={'class':'a-icon-alt'}).string.strip()
        except:
            rating = ""	

    return rating

def get_review_count(soup):
    try:
        review_count = soup.find("span", attrs={'id':'acrCustomerReviewText'}).string.strip()

    except AttributeError:
        review_count = ""	

    return review_count

       
    


if __name__ == "__main__":

   URL = "https://www.amazon.in/s?k=tablets&crid=34YS3OESTKLUG&sprefix=%2Caps%2C212&ref=nb_sb_ss_recent_1_0_recent"
   HEADERS =({'User-Agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36','Accept-Language':'en-US, en;q=0.5'})

   webpage = requests.get(URL,headers=HEADERS)

   if webpage.status_code ==200:
       soup = BeautifulSoup(webpage.content,"html.parser")
   else:
    raise Exception(f"Received a {webpage.status_code} response. Stopping the script.")
     
  
   


links = soup.find_all("a",attrs={'class':"a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal"})

links_list=[]
for link in links:
      links_list.append(link.get('href'))

d={'title':[],'price':[],'rating':[],'reviews':[],'URL':[]}
     
for link in links_list:

      new_webpage=requests.get("https://www.amazon.in/"+ link, headers=HEADERS)

      new_soup= BeautifulSoup(new_webpage.content ,"html.parser")

      d['title'].append(get_title(new_soup))
      d['price'].append(get_price(new_soup))
      d['rating'].append(get_rating(new_soup))
      d['reviews'].append(get_review_count(new_soup))
      d['URL'].append("https://www.amazon.in/"+ link)

    
    
 

amazon_df = pd.DataFrame.from_dict(d)
amazon_df['title'].replace('', np.nan, inplace=True)
amazon_df = amazon_df.dropna(subset=['title'])
amazon_df.to_csv("amazon_data.csv", header=True, index=False)
print("succesful")
print(amazon_df)


 






   
   