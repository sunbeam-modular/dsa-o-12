import httplib2
import re
from bs4 import BeautifulSoup, SoupStrainer
import sys
import traceback

# <a href="page2.html">Page 2</a>
domain = 'sunbeaminfo.in'
website = 'http://' + domain + '/'
http = httplib2.Http()
parser = 'html.parser'
vertices = list()
graph = list()
marked = list()
q = list()
q.append(website)
while len(q) > 0:
    trav = q.pop(0)
    try:
        adjlist = list()
        if trav in vertices:
            index = vertices.index(trav)
            adjlist = graph[index]
        else:
            vertices.append(trav)
            graph.append(adjlist)
            marked.append(True)
        print('requesting -- ' + trav)
        header, response = http.request(trav)
        if header['status'] == '200':
            for link in BeautifulSoup(response, parser, parse_only=SoupStrainer('a')):
                if link.has_attr('href'):
                    url = link['href']
                    if link is not None and not url.startswith('http'):
                        url = website + url
                    skip_pattern = '(\.png|\.jpg|\.js|\.css|\.gif|\.pdf)$'
                    m = re.search(skip_pattern, url.lower())
                    if m:
                        print('-- skipping -- ' + url)
                        continue
                    m = re.search('https?://([A-Za-z_0-9.-]+).*', url)
                    if m and m.group(1).find(domain) != -1 and url.find('#') == -1 and url not in q:
                        if url in vertices:
                            index = vertices.index(url)
                        else:
                            vertices.append(url)
                            graph.append(list())
                            marked.append(False)
                            index = len(vertices)-1
                        if marked[index] == False:
                            marked[index] = True
                            q.append(url)
                        if url not in adjlist:
                            adjlist.append(url)
            # print(q)
    except Exception as e:
        print(e)
        # traceback.print_exc(file=sys.stdout)

print(f"num of pages 1: {len(vertices)}")
print(f"num of pages 2: {len(graph)}")

for i in range(len(graph)):
    print(vertices[i], end=' -> ')
    print(graph[i])
