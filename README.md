# tree_printer
A Java class for printing binary trees as ASCII text

The Java class TreePrinter has two static methods for printing binary trees as ASCII text.

PrintTree prints a single tree.  In addition to the tree, it takes a positive integer parameter 'hspace' which is the minimum number of horizontal spaces between any two node labels in the tree.

PrintTrees prints a list of trees horizontally across the page, in multiple rows if necessary.  In addition to the tree and 'hspace' parameter, it takes a 'tspace' parameter which is the number of space columns between trees horizontally, as well as the number of blank lines between rows of trees, and a 'lineWidth' parameter which is the maximum width of the page.

A few test/demo programs are included.  For instance, EnumTrees can be used to print an enumeration of all binary trees of a given size.  All trees of size 5, labeled with number word (one, two, etc) is printed as:

```
one           one         one          one        one       one         one       one          one      
  \             \           \            \          \         \           \         \            \      
  two           two         two          two        two      three       three      four         four   
    \             \           \            \          \       / \         / \       / \          / \    
   three         three        four         five       five  two four    two five  two five      /   \   
      \             \         / \          /          /           \         /       \        three  five
      four          five     /   \      three       four          five    four     three      /         
        \           /     three  five      \        /                                       two         
        five      four                     four  three                                                  



one        one       one       one         one        two             two          two          two   
  \          \         \         \           \        / \             / \          / \          / \   
  five       five      five      five        five    /   \           /   \       one four     one five
  /          /         /         /           /     one  three      one  three        / \          /   
two        two      three      four        four            \               \        /   \      three  
  \          \       / \       /           /               four            five  three  five      \   
 three       four  two four  two        three                \             /                      four
    \        /                 \         /                   five        four                         
    four  three               three    two                                                            



   two      three         three         three         three      four      four         four  
   / \       / \           / \           / \           / \       / \       / \          / \   
 one five  one four       /   \        two four      two five  one five  one five     two five
     /       \   \       /     \       /     \       /   /       \         \          / \     
   four      two five  one     five  one     five  one four      two      three      /   \    
   /                     \     /                                   \       /       one  three 
three                    two four                                 three  two                  



     four            four       five        five      five      five      five       five         five
     / \             / \        /           /         /         /         /          /            /   
    /   \           /   \     one         one       one       one       one        two          two   
 three  five     three  five    \           \         \         \         \        / \          / \   
  /               /             two         two      three      four      four    /   \       one four
one             two               \           \       / \       /         /     one  three        /   
  \             /                three        four  two four  two      three            \      three  
  two         one                   \         /                 \       /               four          
                                    four   three               three  two                             



    five        five      five      five         five        five          five
    /           /         /         /            /           /             /   
 three       three      four      four         four        four          four  
  / \         / \       /         /            /           /             /     
one four    two four  one       one          two        three         three    
  \         /           \         \          / \         /             /       
  two     one           two      three      /   \      one           two       
                          \       /       one  three     \           /         
                         three  two                      two       one         
```
RandomTree can be used to print a single randomly generated tree, such as the following:
```
                seven                                              
                / \                                               
               /   \                                              
              /     \                                             
             /       \                                            
            /         \                                           
           /           \                                          
         five        sixteen                                      
         / \           / \                                        
        /   \         /   \                                       
       /     \       /     \                                      
    three    six    /       \                                     
     / \           /         \                                    
    /   \         /           \                                   
  two   four     /             \                                  
  /             /               \                                 
one            /                 \                                
              /                   \                               
             /                     \                              
            /                       \                             
           /                         \                            
          /                           \                           
         /                             \                          
       ten                          twenty two                    
       / \                             / \                        
      /   \                           /   \                       
     /     \                         /     \                      
    /       \                       /       \                     
   /         \                     /         \                    
eight      fifteen                /           \                   
   \         /                   /             \                  
   nine   eleven            twenty one     twenty three           
             \                 /                 \                
            twelve         eighteen          twenty four          
               \             / \                   \              
             thirteen       /   \              twenty seven       
                 \         /     \                 / \            
               fourteen   /       \               /   \           
                         /         \             /     \          
                    seventeen    nineteen       /       \         
                                     \         /         \        
                                    twenty    /           \       
                                         twenty six   twenty eight
                                            /               \     
                                      twenty five          thrity 
                                                            /     
                                                      twenty nine 
```

