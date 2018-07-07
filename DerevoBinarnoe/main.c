#include <stdio.h>
#include <stdlib.h>
//Значение узла дерева
typedef int t_btree_key;

//Структура узла
typedef struct t_btree_node{
struct t_btree_node *child[2];
int diff;
t_btree_key key;
}t_btree_node;

//Структура дерева
typedef struct {
t_btree_node *root;
}t_btree;

t_btree_node* balancirovka(t_btree_node* node);

//Конструктор дерева
t_btree* btree_new()
{
    t_btree *tree;
    tree=(t_btree *)malloc(sizeof(t_btree));
    tree->root=NULL;
    return tree;
}
//Деструктор дерева
void btree_del (t_btree *tree)
{
    void btree_del_node(t_btree_node *node)
    {
        if (node==NULL)
        {
            return;
        }
        btree_del_node(node->child[0]);
        btree_del_node(node->child[1]);
        free(node);
    }
    if (tree==NULL)
    {
        return;
    }
    btree_del_node(tree->root);
}
//Добавление элементов в дерево
int btree_add (t_btree *tree,t_btree_key key)
{
    t_btree_node* btree_new_node()
    {
        t_btree_node *node;
        node=(t_btree_node *)malloc(sizeof(t_btree_node));
        node->child[0]=NULL;
        node->child[1]=NULL;
        node->key=key;
        return node;
    }

    t_btree_node* btree_add_node(t_btree_node *node)
    {
        if (node==NULL)
        {
            return btree_new_node ();
        }
        int i=(key>=node->key);
        node->child[i]=btree_add_node(node->child[i]);
        node=balancirovka(node);
        return node;
    }
    tree->root=btree_add_node(tree->root);

}
//Вывод дерева на экран
void btree_out (t_btree *tree)
{
    void btree_out_node(t_btree_node *node)
    {
        if (node==NULL)
        {
            return;
        }
        btree_out_node(node->child[0]);
        btree_out_node(node->child[1]);
        printf("%i\n",node->key);
    }
    if (tree==NULL)
    {
        return;
    }
    btree_out_node(tree->root);
}

//Функции для работы с поворотами
//1
unsigned char diff(t_btree_node* node)
{
    if (node==NULL)
    {
        node->diff=0;
    }
}

//2
int diferent(t_btree_node* node)
{
    return (diff(node->child[1])-diff(node->child[0]));
}

//3
void fixdiff(t_btree_node* node)
{
    unsigned char difR = diff(node->child[0]);
    unsigned char difL = diff(node->child[1]);
    if (difR>difL)
    {
        node->diff=difR+1;
    }
    else
    {
        node->diff=difL+1;
    }
}

//4
//Правый поворот
t_btree_node* pravie(t_btree_node* node)
{
    t_btree_node* q = node->child[0];
    node->child[0] = q->child[1];
    q->child[1] = node;
    fixdiff(node);
    fixdiff(q);
    return q;
}

//5
// Левый поворот
t_btree_node* levie(t_btree_node* node)
{
    t_btree_node* p = node->child[1];
    node->child[1] = p->child[0];
    p->child[0] = node;
    fixdiff(node);
    fixdiff(p);
    return p;
}

//6
// балансировка узла
t_btree_node* balancirovka(t_btree_node* node)
{
    fixdiff(node);
    if( diferent(node)==2 )
    {
        if( diferent(node->child[1]) < 0 )
        {
            node->child[1] = pravie(node->child[0]);
        }
        return levie(node);
    }
    if( diferent(node)==-2 )
    {
        if( diferent(node->child[0]) > 0  )
        {
            node->child[0] = levie(node->child[0]);
        }
        return pravie(node);
    }
    return node;
}

void main()
{
    t_btree* TREE;
    TREE=btree_new();
    btree_add(TREE,5);
    btree_add(TREE,54);
    btree_add(TREE,1);
    btree_add(TREE,104);
    btree_out(TREE);
    btree_del(TREE);
}
