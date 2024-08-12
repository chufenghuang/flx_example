package domainType;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import cf.lang.Content;
import commoncode.LiteralScanner;
import ddt.lang.Dinteger;
import partialOrderPlanner.CompositeContent;

public class DListIterator
      implements Content<Dinteger> {
   Iterator iter;
   List l;

   public DListIterator() {
      this.l = null;
      this.iter = null;
   }

   public DListIterator(List x) {
      this.l = x;
      this.iter = this.l.iterator();
   }

   public List getList() {
      return this.l;
   }

   public void reset() {
      this.iter = this.l.iterator();
   }

   public Object next() {
      return this.iter.next();
   }

   public boolean hasNext() {
      return this.iter.hasNext();
   }

   public boolean contentContains(Dinteger x) {
      return l.contains(x);
   }

   public boolean contentIsEmpty() {
      return l.isEmpty();
   }

   public static boolean combinationFunc(HashSet<String> group, String groupType, String elementType,
         Hashtable<String, HashSet<String>> groupSatCon) {
      CompositeContent tester = new CompositeContent();
      boolean sat = false;
      for (Iterator<String> literalIterator = group.iterator(); literalIterator.hasNext();) {
         String currentLiteral = literalIterator.next();
         LiteralScanner scanner = new LiteralScanner(currentLiteral);
         String primeVar = scanner.getPrimeVariable();
         String subVar = scanner.getSubVariable();
         String predicateMethod = scanner.getPredicateMethod();
         boolean isNegative = scanner.isNegative();
         if (predicateMethod.equals("contentContains")) {
            if (!isNegative) {
               tester.add("Contains", primeVar, subVar);
            } else {
               tester.add("NotContains", primeVar, subVar);
            }
         } else if (predicateMethod.equals("contentIsEmpty")) {
            if (!isNegative) {
               tester.add("IsEmpty", primeVar, null);
            } else {
               tester.add("NotEmpty", primeVar, null);
            }
         } else {
            System.err.println("unrecognized predicate method name : " + predicateMethod);
            System.exit(1);
         }
      }
      sat = tester.isSatisfiable();
      if (groupSatCon == null) {
         return sat;
      }
      if (sat == true) {
         HashSet<String> elementSatCon = new HashSet<String>();
         if (groupSatCon.contains(groupType)) {
            HashSet<String> groupTmp = groupSatCon.get(groupType);
            groupTmp.addAll(group);
         } else {
            groupSatCon.put(groupType, group);
         }
         elementSatCon = tester.getElementSatCon(elementType);
         boolean hasAdd = false;
         for (Enumeration<String> keysEnum = groupSatCon.keys(); keysEnum.hasMoreElements();) {
            String keyTmp = keysEnum.nextElement();
            if (keyTmp.equals(elementType)) {
               HashSet<String> groupTmp = groupSatCon.get(elementType);
               groupTmp.addAll(elementSatCon);
               hasAdd = true;
               break;
            }
         }
         if (hasAdd == false) {
            groupSatCon.put(elementType, elementSatCon);
         }
      }
      return sat;
   }
}