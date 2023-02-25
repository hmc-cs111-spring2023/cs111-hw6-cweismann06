package machines

import regex._
import dfa._
import machines._

// TODO: Add your code below
given Conversion[Char, RegularLanguage] = Character(_)

given Conversion[String, RegularLanguage] = s => s.map(Character(_)).reduce(Concat(_,_))

/*
A. implement the binary operator ||, which corresponds to the union operation
B. implement the binary operator ~, which corresponds to the concatenation operation
C. implement the postfix operator <*>, which corresponds to the Kleene star operation
D. implement the postfix operator <+>, which means "one or more repetitions of the preceding pattern"
E. implement the repetition operator {n} which means "n repetitions of the preceding pattern"
*/

extension (r: RegularLanguage)
    def ||(s: RegularLanguage) = Union(r,s)
    def ~(s: RegularLanguage) = Concat(r,s)
    def <*> = Star(r)
    def <+> = Concat(r,Star(r))
    def apply(n: Int): RegularLanguage =
        if n <= 0 then Empty
        else Array.fill(n)(r).reduce(Concat(_,_))
    def toDFA(using s: Set[Char]) = regexToDFA(r, s)