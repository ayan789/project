# -*- coding: UTF-8 -*-
# Filename : helloworld.py
# author by : www.runoob.com
# 该实例输出 Hello World!
import os
import requests
from lxml import etree

headers = {
    'User-Agent':
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_1) AppleWebKit/537.36 (KHTML, like Gecko)\
 Chrome/70.0.3538.102 Safari/537.36'
}

def get_last_page(html_text):
    """获取最后一页的页码"""
    selector = etree.HTML(html_text)
    last_page = selector.xpath('//ul[@class="pagination"]/li[last()-1]/a/text()')[0]
    return int(last_page)

def get_img_urls(html_text):
    """解析HTML，并提取图片地址"""
    selector = etree.HTML(html_text)
    img_urls = selector.xpath('//li[@class="list-group-item"]//a/img/@data-original')
    return [img_url.replace('!dta', '') for img_url in img_urls]

def img_downloader(save_folder, img_url):
    """下载图片"""
    with requests.get(img_url, stream=True, headers=headers) as r:
        filename = os.path.basename(img_url)
        with open(os.path.join(save_folder, filename), 'wb') as f:
            for chunk in r.iter_content(512):
                f.write(chunk)

if __name__ == '__main__':
    print('Hello World pc!')
    url = 'http://www.doutula.com/photo/list/'
    response = requests.get(url, headers=headers)
    print(response.text)
    last_page = get_last_page(response.text)
    print(last_page)
    save_folder = '/Users/youth.s/Documents/AAA/imgs2'
    if not os.path.exists(save_folder):
        os.mkdir(save_folder)
    for page in range(1, last_page + 1):
        response = requests.get(url, params={'page': page}, headers=headers)
        img_urls = get_img_urls(response.text)
        for img_url in img_urls:
            print('正在下载：{}'.format(img_url))
            img_downloader(save_folder, img_url)