# asynctor

Minimal core.async inspector library for Clojure(Script).

## Install

[![Asynctor](https://img.shields.io/clojars/v/asynctor.svg)](https://clojars.org/asynctor)

## Usage

```clojure
(:require [asynctor.core :as tr])
```

### inspect
```clojure
  (let [c (chan 10)]
    (dotimes [x 10]
      (>!! c x))
    (tr/inspect c))
    
    ;;=> {:buffer [0 1 2 3 4 5 6 7 8 9], :puts [], :takes []}
```

### buffer (current items in the channel)
```clojure
  (let [c (chan 10)]
    (dotimes [x 10]
      (>!! c x))
    (tr/buffer c))
    
    ;;=> [0 1 2 3 4 5 6 7 8 9]
```

### puts (waiting puts)
```clojure
  (let [c (chan)]
    (dotimes [x 10]
      (put! c x))
    (tr/puts c))
    
    ;;=> [0 1 2 3 4 5 6 7 8 9]
```

### takes (waiting takes)
```clojure
  (let [c (chan)]
    (dotimes [x 10]
      (take! c (fn [])))
    (tr/takes c))
    
    ;;=> [#object[clojure.core.async$fn_h...]

```

## License
```
  Copyright © 2019 Ertuğrul Çetin
  
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  
  http://www.apache.org/licenses/LICENSE-2.0
  
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
```
