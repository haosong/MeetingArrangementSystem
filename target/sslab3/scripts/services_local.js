'use strict';

angular.module('adWebHW')
    .service('bookFactory', function () {
        var books = [
            {
                "id": 0,
                "name": "Beginning Java EE 7",
                "image": "images/JavaEE7.jpg",
                "category": "javaee",
                "label": "Hot",
                "price": 45.57,
                "amazon": "http://www.amazon.com/Beginning-Java-EE-Expert-Voice/dp/143024626X",
                "description": "Java Enterprise Edition (Java EE) continues to be one of the leading Java technologies and platforms. Beginning Java EE 7 is the first tutorial book on Java EE 7.",
                "comments": [
                    {
                        "rating": 1,
                        "comment": "This JavaEE Book is Naive !",
                        "author": "HaoSong",
                        "date": "2016-05-04T12:49:10.550Z"
                    },
                    {
                        "rating": 3,
                        "comment": "Beginning Java EE 7 is Simple !",
                        "author": "SongHao",
                        "date": "2016-02-05T12:48:44.634Z"
                    },
                    {
                        "rating": 5,
                        "comment": "This 'Beginning Java EE 7' book is Excited !",
                        "author": "Elder",
                        "date": "2015-07-01T12:47:58.145Z"
                    }
                ]
            },
            {
                "id": 1,
                "name": "Inside XML",
                "image": "images/InsideXML.jpg",
                "category": "xml",
                "label": "",
                "price": 11.09,
                "amazon": "http://www.amazon.com/Inside-XML-New-Riders/dp/0735710201",
                "description": "Inside XML is an intelligent and easy-to-follow guide to today proliferating XML standards. Aside from being a road map to the latest and greatest in what is on the horizon with XML, this book gives you what you need to know to be productive with existing XML tools right now.",
                "comments": [
                    {
                        "rating": 1,
                        "comment": "This Inside XML Book is Naive !",
                        "author": "HaoSong",
                        "date": "2016-05-04T12:49:10.550Z"
                    },
                    {
                        "rating": 3,
                        "comment": "Inside XML is Simple !",
                        "author": "SongHao",
                        "date": "2016-02-05T12:48:44.634Z"
                    },
                    {
                        "rating": 5,
                        "comment": "This 'Inside XML' book is Excited !",
                        "author": "Elder",
                        "date": "2015-07-01T12:47:58.145Z"
                    }
                ]
            },
            {
                "id": 2,
                "name": "Learning XML",
                "image": "images/LearningXML.jpg",
                "category": "xml",
                "label": "New",
                "price": 29.65,
                "amazon": "http://www.amazon.com/Learning-XML-Second-Erik-Ray/dp/0596004206",
                "description": "This second edition of the bestselling Learning XML provides web developers with a concise but grounded understanding of XML (the Extensible Markup Language) and its potential-- not just a whirlwind tour of XML.The author explains the important and relevant XML technologies and their capabilities clearly and succinctly with plenty of real-life projects and useful examples.",
                "comments": [
                    {
                        "rating": 1,
                        "comment": "This Learning XML Book is Naive !",
                        "author": "HaoSong",
                        "date": "2016-05-04T12:49:10.550Z"
                    },
                    {
                        "rating": 3,
                        "comment": "Learning XML is Simple !",
                        "author": "SongHao",
                        "date": "2016-02-05T12:48:44.634Z"
                    },
                    {
                        "rating": 5,
                        "comment": "This 'Learning XML' book is Excited !",
                        "author": "Elder",
                        "date": "2015-07-01T12:47:58.145Z"
                    }
                ]
            },
            {
                "id": 3,
                "name": "Learning Three.js: The JavaScript 3D Library for WebGL",
                "image": "images/LearningThreeJS.jpg",
                "category": "web3d",
                "label": "",
                "price": 49.99,
                "amazon": "http://www.amazon.com/Learning-Three-js-JavaScript-Library-Second/dp/1784392219",
                "description": "Enhance your 3D graphics with light sources, shadows, advanced materials, and textures. Load models from external sources, and visualize and animate them directly from JavaScript. Each subject is explained using extensive examples that you can use directly and adapt for your own purposes.",
                "comments": [
                    {
                        "rating": 1,
                        "comment": "This Learning Three.js Book is Naive !",
                        "author": "HaoSong",
                        "date": "2016-05-04T12:49:10.550Z"
                    },
                    {
                        "rating": 3,
                        "comment": "Learning Three.js is Simple !",
                        "author": "SongHao",
                        "date": "2016-02-05T12:48:44.634Z"
                    },
                    {
                        "rating": 5,
                        "comment": "This 'Learning Three.js' book is Excited !",
                        "author": "Elder",
                        "date": "2015-07-01T12:47:58.145Z"
                    }
                ]
            },
            {
                "id": 4,
                "name": "PhoneGap: Beginners Guide, 3rd Edition",
                "image": "images/Phonegap.jpg",
                "category": "hybrid",
                "label": "",
                "price": 44.99,
                "amazon": "http://www.amazon.com/Learning-Three-js-JavaScript-Library-Second/dp/1784392219",
                "description": "This book is for web developers who want to be productive in the mobile market quickly. In fact, by using PhoneGap, it is possible to deploy native applications based on web standards. This book assumes a very small knowledge of HTML/CSS/JavaScript and mobile platforms, such as Android, BlackBerry, iOS, and Windows Phone, and takes the reader step-by-step into a deep overview of PhoneGap and its APIs.",
                "comments": [
                    {
                        "rating": 1,
                        "comment": "This PhoneGap Book is Naive !",
                        "author": "HaoSong",
                        "date": "2016-05-04T12:49:10.550Z"
                    },
                    {
                        "rating": 3,
                        "comment": "Inside XML is Simple !",
                        "author": "SongHao",
                        "date": "2016-02-05T12:48:44.634Z"
                    },
                    {
                        "rating": 5,
                        "comment": "This 'PhoneGap: Beginners Guide' book is Excited !",
                        "author": "Elder",
                        "date": "2015-07-01T12:47:58.145Z"
                    }
                ]
            },
            {
                "id": 5,
                "name": "Learning Ionic - Build Hybrid Mobile Applications with HTML5",
                "image": "images/LearningIonic.jpg",
                "category": "hybrid",
                "label": "",
                "price": 36.49,
                "amazon": "http://www.amazon.com/Learning-Three-js-JavaScript-Library-Second/dp/1784392219",
                "description": "Create hybrid mobile applications by combining the capabilities of Ionic, Cordova, and AngularJS. Reduce the time to market your application using Ionic, that helps in rapid application development. Detailed code examples and explanations, helping you get up and running with Ionic quickly and easily",
                "comments": [
                    {
                        "rating": 1,
                        "comment": "This Learning Ionic Book is Naive !",
                        "author": "HaoSong",
                        "date": "2016-05-04T12:49:10.550Z"
                    },
                    {
                        "rating": 3,
                        "comment": "Learning Ionic is Simple !",
                        "author": "SongHao",
                        "date": "2016-02-05T12:48:44.634Z"
                    },
                    {
                        "rating": 5,
                        "comment": "This 'Learning Ionic' book is Excited !",
                        "author": "Elder",
                        "date": "2015-07-01T12:47:58.145Z"
                    }
                ]
            }
        ];

        this.getBooks = function () {
            return books;
        };

        this.getBook = function (index) {
            return books[index];
        };
    });