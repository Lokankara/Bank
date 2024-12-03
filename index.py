import requests
from bs4 import BeautifulSoup
from deep_translator import GoogleTranslator

base_url = "http://javainsimpleway.com/"
pages = ["hybris-installation", "defining-collection-in-items-xml"
#    "indexes-intro.html", "indexes-types.html", "indexes-multicolumn.html", 
 #   "indexes-ordering.html", "indexes-bitmap-scans.html", "indexes-unique.html", 
  #  "indexes-expressional.html", "indexes-partial.html", "indexes-index-only-scans.html", 
   # "indexes-opclass.html", "indexes-collations.html", "indexes-examine.html"
]
max_pages = len(pages)

for page_count in range(max_pages):
    current_page = pages[page_count]
    url = base_url + current_page
    response = requests.get(url)
    
    if response.status_code != 200:
        break

    soup = BeautifulSoup(response.text, 'html.parser')
    sect1 = soup.find(class_='post-content-body')
    if not sect1:
        break

    with open(f"{current_page}.html", "w", encoding="utf-8") as f:
        for element in sect1.find_all(['p', 'pre', 'h2']):
            if element.name == 'p':
                eng_text = element.get_text()
                # ru_text = GoogleTranslator(source='en', target='ru').translate(eng_text)
                f.write(f"<p>{eng_text}</p>")

            elif element.name == 'h2':
                h2_text = element.get_text()
                f.write(f"<h2>{h2_text}</h2>\n")
                
            elif element.name == 'pre':
               pre_text = element.get_text()
               f.write(f"<pre>{pre_text}</pre>\n\n")
