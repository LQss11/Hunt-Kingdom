<?php

namespace ProduitBundle\Repository;

/**
 * ProduitRepository
 *
 * This class was generated by the Doctrine ORM. Add your own custom
 * repository methods below.
 */
class ProduitRepository extends \Doctrine\ORM\EntityRepository
{
    public function  searchPrixCroissant(){
        $query=$this->getEntityManager()
            ->createQuery("SELECT p FROM ProduitBundle:Produit p order by p.prix");
        return $query->getResult();
    }
    public function  searchPrixDecroissant(){
        $query=$this->getEntityManager()
            ->createQuery("SELECT p FROM ProduitBundle:Produit p order by p.prix desc");
        return $query->getResult();
    }
    public function  searchName(){
        $query=$this->getEntityManager()
            ->createQuery("SELECT p FROM ProduitBundle:Produit p order by p.nom");
        return $query->getResult();
    }
    public function  searchPromo(){
        $query=$this->getEntityManager()
            ->createQuery("SELECT p FROM ProduitBundle:Produit p where p.etatPromo=1");
        return $query->getResult();
    }
    public function listeWish()
    {
        $query = $this->getEntityManager()

            ->createQuery(' SELECT u FROM  ProduitBundle:Produit u  JOIN  ProduitBundle:Whishlist b where u.id =: b.idProduit  ');
        return $query->getResult();
    }
    public function mefind($name){
        $query=$this->getEntityManager()
            ->createQuery("select m from ProduitBundle:Produit m where m.nom like :name ");
        $query->setParameter(":name",'%'.$name.'%');
        return $query->getResult();
    }
    public function findArray($array)
    {
        $qb = $this->createQueryBuilder('u')
            ->Select('u')
            ->Where('u.id IN (:array)')
            ->setParameter('array', $array);
        return $qb->getQuery()->getResult();
    }

    public function byCategorie($categorie)
    {
        $qb = $this->createQueryBuilder('u')
            ->select('u')
            ->where('u.categorie = :categorie')
            ->orderBy('u.id')
            ->setParameter('categorie', $categorie);
        return $qb->getQuery()->getResult();
    }

    public function recherche($chaine)
    {
        $qb = $this->createQueryBuilder('u');
        $qb->select('u')
            ->where($qb->expr()->like('u.nom', ':chaine'))
            ->orderBy('u.id')
            ->setParameter('chaine', '%'.$chaine.'%');
        return $qb->getQuery()->getResult();
    }

    public function RandomProducts($nbProduits, $categorie)
    {
        $qbList=$this->createQueryBuilder('u');

        // get all the relevant id's from the entity
        $qbList ->select('u.id')
            ->where('u.categorie = :categorie')
            ->setParameter('categorie', $categorie);

        // $list is not a simple list of values, but an nested associative array
        $list=$qbList->getQuery()->getScalarResult();

        $qbcount=$this->createQueryBuilder('c')
            ->select('COUNT(c)')
            ->where('c.categorie = :categorie')
            ->setParameter('categorie', $categorie)
            ->getQuery()
            ->getSingleScalarResult();

        // get rid of the nested array from ScalarResult
        $rawlist=array();
        foreach ($list as $keyword=>$value)
        {
            // entity id's have to figure as keyword as array_rand() will pick only keywords - not values
            $id=$value['id'];
            $rawlist[$id]=null;
        }

        if ($qbcount >= $nbProduits)
        {
            $nbSlotsOnPage = 4;
        }else{
            $nbSlotsOnPage = $qbcount;
        }

        $total=min($nbSlotsOnPage,count($rawlist));
        // pick only a few (i.e.$total)
        $keylist=array_rand($rawlist,$total);

        $qb=$this->createQueryBuilder('uw');
        if (is_array($keylist))
        {
            foreach ($keylist as $keyword=>$value)
            {
                $qb ->setParameter('keyword'.$keyword,$value)
                    ->orWhere('uw.id = :keyword'.$keyword)
                ;
            }
        }else{
            $qb ->setParameter('keyword'.$keylist, $keylist)
                ->Where('uw.id = :keyword'.$keylist)
            ;
        }

        $result=$qb->getQuery()->getResult();

        // if mixing the results is also required (could also be done by orderby rand();
        shuffle($result);

        return $result;
    }
    public function wishfind($idp,$id){
        $query=$this->getEntityManager()
            ->createQuery("select m from ProduitBundle:Whishlist m where m.idProduit = :idp and m.idClient = :idc ");
        $query->setParameter(":idp",$idp);
        $query->setParameter(":idc",$id);
        return $query->getResult();
    }


}
