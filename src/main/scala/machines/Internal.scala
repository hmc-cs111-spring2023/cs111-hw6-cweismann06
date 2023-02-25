package machines

import regex._
import dfa._

given Conversion[Char, RegularLanguage] = Character(_)

given Conversion[String, RegularLanguage] = s => s.map(Character(_)).reduce(Concat(_,_))

given Conversion[RegularLanguage, DFA] = r => regexToDFA(r, r.chars)

extension (r: RegularLanguage)
    def ||(s: RegularLanguage) = Union(r,s)
    def ~(s: RegularLanguage) = Concat(r,s)
    def <*> = Star(r)
    def <+> = Concat(r,Star(r))
    def apply(n: Int): RegularLanguage =
        if n <= 0 then Empty
        else Array.fill(n)(r).reduce(Concat(_,_))
    def toDFA(using s: Set[Char]) = regexToDFA(r, s)
    def chars: Set[Char] = r match
        case Empty => Set[Char]()
        case Epsilon => Set[Char]()
        case Character(c: Char) => Set[Char](c)
        case Union(r1: RegularLanguage, r2: RegularLanguage) => r1.chars ++ r2.chars
        case Concat(r1: RegularLanguage, r2: RegularLanguage) => r1.chars ++ r2.chars
        case Star(r0: RegularLanguage) => r0.chars
